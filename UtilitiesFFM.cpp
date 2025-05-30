#include <iostream>
#include <vector>
#include <cmath>
#include <cstring>

extern "C" {

// void printMethod()
void printMethod() {
    std::cout << "Native method called. Printing garbage." << std::endl;
}

// bool trueFalse()
bool trueFalse() {
    std::cout << "BOOL VALUE: 1 (True)" << std::endl;
    return true;
}

// int power(int base, int exponent)
int power(int b, int e) {
    return static_cast<int>(std::pow(b, e));
}


uint8_t* returnAByteArray() {
    std::vector<uint8_t> data = {0, 1, 1};
    size_t size = data.size();

    // Allocate new buffer on heap
    uint8_t* bufferOut = new uint8_t[size];

    // Copy data into allocated buffer
    std::memcpy(bufferOut, data.data(), size);

    std::cout << "Printing Byte Array members..." << std::endl;
    for (auto c : data) std::cout << static_cast<int>(c);
    std::cout << std::endl << std::endl;

    return bufferOut;
}

// void stringManipulator(const char* input, const char** stringArray, int arrayLength, char* output, int outputSize)
void stringManipulator(const char* input, const char** array, int len, char* output, int outSize) {
    std::string s = input;
    std::cout << "NOW IN NATIVE STRING ENVIRONMENT!!" << std::endl;
    std::cout << "Your caller says: " << s << std::endl;

    std::cout << "Now iterating over the given string array." << std::endl;
    for (int i = 0; i < len; i++) {
        std::cout << array[i] << std::endl;
    }

    s.append("::::::THIS IS APPENDED TEXT!!!! WARNING!!! WARNING!!!! :)+++");
    std::strncpy(output, s.c_str(), outSize - 1);
    output[outSize - 1] = '\0'; // null terminate safely
}

} // extern "C"
