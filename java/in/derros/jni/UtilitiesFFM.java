package in.derros.jni;

import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

import static java.lang.foreign.ValueLayout.*;

public class UtilitiesFFM {
    private static final Linker linker = Linker.nativeLinker();
    private static final SymbolLookup lib =
        SymbolLookup.libraryLookup("./../build/libjnitests.dylib", Arena.ofAuto());

    private static final MethodHandle printMethod = linker.downcallHandle(
        lib.find("printMethod").orElseThrow(), FunctionDescriptor.ofVoid()
    );

    private static final MethodHandle trueFalse = linker.downcallHandle(
        lib.find("trueFalse").orElseThrow(), FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
    );

    private static final MethodHandle power = linker.downcallHandle(
        lib.find("power").orElseThrow(),
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
    );

    private static final MethodHandle returnAByteArray = linker.downcallHandle(
        lib.find("returnAByteArray").orElseThrow(),
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, ValueLayout.JAVA_INT
        )
    );

    // private static final MethodHandle stringManipulator = linker.downcallHandle(
    //     lib.find("stringManipulator").orElseThrow(),
    //     FunctionDescriptor.ofVoid(
    //         ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT,
    //         ValueLayout.ADDRESS, ValueLayout.JAVA_INT
    //     )
    // );



    public void printUtil() throws Throwable {
        printMethod.invoke();
    }

    public boolean boolTest() throws Throwable {
        return (boolean) trueFalse.invoke();
    }

    public int pow(int base, int exp) throws Throwable {
        return (int) power.invoke(base, exp);
    }

    public byte[] testReturnBytes() throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment segment = arena.allocate(10);
            returnAByteArray.invoke(segment, 10);
            byte[] result = new byte[10];
            segment.copyFrom(MemorySegment.ofArray(result));
            return result;
        }
    }

    // public String manipulateStrings(String s, String[] s1) throws Throwable {
    //     try (Arena arena = Arena.ofConfined()) {
    //         MemorySegment strSeg = arena.allocateUtf8String(s);
    //         MemorySegment outSeg = arena.allocate(100);
            
    //         MemorySegment[] segments = new MemorySegment[s1.length];
    //         for (int i = 0; i < s1.length; i++) {
    //             segments[i] = MemorySegment.allocateUtf8String(s1[i], arena);
    //         }
    
    //         MemorySegment arraySeg = MemorySegment.allocateArray(ValueLayout.ADDRESS, segments, arena);
    
    //         stringManipulator.invoke(strSeg, arraySeg, s1.length, outSeg, 100);
    //         return outSeg.getUtf8String(0);
    //     }
    // }
    

    public static void main(String[] args) throws Throwable {
        UtilitiesFFM util = new UtilitiesFFM();
        util.printUtil();
        System.out.println(util.boolTest() + "\n");
        System.out.println(util.pow(2, 2) + "\n\n");
        byte[] bs = util.testReturnBytes();
        for (byte b : bs) {
            System.out.println("A Byte is: " + b);
        }
        System.out.println("THIS IS THE STRING MANIPULATOR!!");
        // System.out.println(util.manipulateStrings("asdfxvcbiojdasaisdf hello world,,,", args));
    }
}
