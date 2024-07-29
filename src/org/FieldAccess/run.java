package org.FieldAccess;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;



public class run {



  public static void main(String[] args) throws Exception {
    // Use standard approach for getting private variable's value
    SecretAgent secretAgent = new SecretAgent(18);
    System.out.println("Old secretNumber field : " + secretAgent.getSecretName());
    
    // Import Field package to get a specific private member in the class
    Field valueField = SecretAgent.class.getDeclaredField("secretNumber");
    // Change the default setting to make sure the visiting access is opened
    valueField.setAccessible(true);
    // Compilation
    VarHandle valueHandle = MethodHandles.privateLookupIn(SecretAgent.class, MethodHandles.lookup()).unreflectVarHandle(valueField);

    MemoryLayout layout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("id"),
            MemoryLayout.paddingLayout(4),          
            ValueLayout.ADDRESS.withName("name")      
        );

    // Arrange a piece of memory to the compiled private variable; change the variable's stored value by visiting its address.
    try (Arena arena = Arena.ofConfined()) {
      // Memory allocation
      MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT);
      System.out.println(segment.get(ValueLayout.JAVA_INT, 0));
      // change the value
      segment.set(ValueLayout.JAVA_INT, 0, 52);
      System.out.println(segment);
      
      int newValue = segment.get(ValueLayout.JAVA_INT, 0);
      valueHandle.set(secretAgent, newValue);

      
      segment = arena.allocate(layout);
      System.out.println(segment);
      VarHandle idHandle = layout.varHandle(MemoryLayout.PathElement.groupElement("id"));
      idHandle.set(segment, 123);
      int id = (int) idHandle.get(segment);
      System.out.println(id);
      String st = "Hello, World!";
      MemorySegment nativeText = arena.allocateUtf8String(st);
      System.out.println(nativeText);

    }
    // Output the updated value to guarantee if the private value has been changed
    System.out.println("New secretNumber field: " + secretAgent.getSecretName());

    

    
  }
}