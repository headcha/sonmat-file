package com.sonmat.file.domain.file;

import java.nio.file.Paths;
import java.util.Random;

public class FileSavePathGenerator {

    public static final String BASE_FILE_SAVE_PATH = "/file/";
    private static final int MAX_DIR_NAME = 10001;

    public static String generate() {
        int randomDirName = new Random().nextInt(MAX_DIR_NAME);
        return Paths.get(BASE_FILE_SAVE_PATH , String.valueOf(randomDirName)).toString();
    }
}
