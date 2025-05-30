# cmake_minimum_required(VERSION 2.8)
# project(jnitests)
# set(CMAKE_BUILD_TYPE Release)
# # find_package(JNI REQUIRED)
# # include_directories(${JNI_INCLUDE_DIRS})
# # Manually set JAVA_HOME if not auto-detected
# set(JAVA_HOME "/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home")  # adjust path if needed

# # Set JNI include paths explicitly
# set(JAVA_INCLUDE_PATH "${JAVA_HOME}/include")
# set(JAVA_INCLUDE_PATH2 "${JAVA_INCLUDE_PATH}/darwin")


# include_directories(${JAVA_INCLUDE_PATH})
# include_directories(${JAVA_INCLUDE_PATH2})
# set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
# set(SOURCE_FILES UtilitiesFFM.cpp)
# add_library(jnitests SHARED ${SOURCE_FILES})
cmake_minimum_required(VERSION 3.0)
project(jnitests)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_BUILD_TYPE Release)

add_library(jnitests SHARED UtilitiesFFM.cpp)
