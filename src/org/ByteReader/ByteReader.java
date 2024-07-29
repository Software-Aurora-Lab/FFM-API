package org.ByteReader;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

public class ByteReader {
  public static MemorySegment segment;

  public static MemorySegment getSegment() throws Throwable {
    
    return segment;
  }
}
