Questdb: In this file, the functions “arrayGetVolatile”, “arrayPutOrdered”, “byteArrayGetInt”, “byteArrayGetLong” and “cas” have been transformed to FFM + MethodHandles + VarHandle.
https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/Questdb/core/src/main/java/io/questdb/std/Unsafe.java 
Newly added FFM class in Questdb: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/Questdb/core/src/main/java/io/questdb/std/FFM.java
    


Bazel: In this folder, the self-created FFMProvider has been imported into the Build file. Also, in StringUnsafe.java, the place where it originally used Unsafe has been transformed to use MethodHandle and VarHandle. 

Folder’s link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/tree/main/Bazel/src/main/java/com/google/devtools/build/lib/unsafe



Kryo: In this folder, the project provides three input files: “Input.java”, “ByteBufferInput.java”, “UnsafeInput.java”, and "UnsafeByteBufferInput.java”. The result shows that each of them outputs the same result. That means Kryo becomes a safe version once it solely uses “ByteBufferInput.java” as the input.
https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/tree/main/Kryo/src/com/esotericsoftware/kryo/io



Mapdb: In Commit 4340d75, the project is in the Unsafe version. In Commit be0e111, the project has become a safe version by replacing “Unsafe” with “ByteBuffer”.

The place where  Commit 4340d75 uses Unsafe: https://github.com/jankotek/mapdb/blob/4340d75895319cecc40b3a616915acdf77bf0fc0/src/test/java/org/mapdb/UnsafeStuffTest.java

The place where Commit be0e111 uses ByteBuffer: https://github.com/jankotek/mapdb/blob/be0e11155479b96290517675a0e1801042e1c85e/src/main/java/org/mapdb/volume/ByteBufferVol.java









 Protostuff: On the file “RunTimeFieldFactory.java”, from line 173 to line 222, it includes an “if-else” statement so that the project can run without using Unsafe. In the “else” statement, when Unsafe is no longer used, the project uses a tool class called “RuntimeReflectionFieldFactory”. (The original project is designed to prevent the usage of Unsafe. At the same time, it provides an Unsafe access because nowadays Unsafe is used by many people.)


RunTimeFieldFactory.java link:
https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/protostuff/protostuff-runtime/src/main/java/io/protostuff/runtime/RuntimeFieldFactory.java

RuntimeReflectionFieldFactory link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/protostuff/protostuff-runtime/src/main/java/io/protostuff/runtime/RuntimeReflectionFieldFactory.java


Javassist: On the file “DefineClassHelper.java”, from line 236 to line 318, the project includes three “toClass” methods. Each of them uses “java.lang.invoke.MethodHandle” and avoid using Unsafe unless the loaded class is null.  This project has already become a safe version because the project’s developers realize the potential risks of the Unsafe class. (line 225 to line 226: Java 11+ removed sun.misc.Unsafe.defineClass, so we fall back to invoking defineClass on
ClassLoader until we have an implementation that uses MethodHandles.Lookup.defineClass)

DefineClassHelper.java link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/javassist/src/main/javassist/util/proxy/DefineClassHelper.java


JOML: On the file “Matrix3f.java”, if the project detects that Unsafe exists, the project will not execute the Unsafe part. ByteBuffer operates the rest of the code. (The project is designed to prevent the use of Unsafe practices by the project’s developers.)

Matrix3f.java link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/JOML/src/main/java/org/joml/Matrix3f.java




Gson: On the file “UnsafeAllocator.java”, the part where the project uses Unsafe has been commented out. Then the project can still pass all tests. However, this update is dubious and can’t be considered as a safe version.

UnsafeAllocator.java link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/Gson/gson/src/main/java/com/google/gson/internal/UnsafeAllocator.java


Embulk: The file “FFM.java” is our self-created FFM class in the project “Atomic Test”. After incorporating this extra class, the project yields the same test results as the original version.

FFM.java’s link in Embulk: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/Embulk/embulk-core/src/main/java/org/embulk/exec/FFM.java


Procyon: On the file “TypeBuilder.java”, from line 1686 to line 1708, the project includes an extra version of the function “DefineClass0()”, which doesn’t use the Unsafe class.

TypeBuilder.java link: https://github.com/Software-Aurora-Lab/Benchmarks-for-LLM/blob/main/procyon/Procyon.Reflection/src/main/java/com/strobel/reflection/emit/TypeBuilder.java
