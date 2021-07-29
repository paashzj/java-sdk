package com.github.shoothzj.javatool.module;

import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
public class ShellResult {

    private int returnCode;

    private String inputContent;

    private String errorContent;

    private Exception error;

    public ShellResult() {
    }

    public ShellResult(String inputContent, String errorContent) {
        this(0, inputContent, errorContent);
    }

    public ShellResult(int returnCode, String inputContent, String errorContent) {
        this(returnCode, inputContent, errorContent, null);
    }

    public ShellResult(int returnCode, String inputContent, String errorContent, Exception error) {
        this.returnCode = returnCode;
        this.inputContent = inputContent;
        this.errorContent = errorContent;
        this.error = error;
    }
}
