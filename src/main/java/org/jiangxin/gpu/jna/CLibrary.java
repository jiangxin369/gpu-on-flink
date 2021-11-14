package org.jiangxin.gpu.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CLibrary extends Library {
    CLibrary INSTANCE = Native.load("matrix_multiplication.so",
                    CLibrary.class);
    int main();
}
