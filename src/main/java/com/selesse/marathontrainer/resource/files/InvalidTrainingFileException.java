package com.selesse.marathontrainer.resource.files;

public class InvalidTrainingFileException extends Exception {
    public InvalidTrainingFileException() {
        this("Invalid/unsupported training file.");
    }

    public InvalidTrainingFileException(String message) {
        super(message);
    }
}
