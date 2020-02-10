package com.hugeinc.drawingtool;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class DrawingToolTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        final String inputFilePath = "src/test/resources/files/input.txt";
        final String outputOriginalFilePath = "src/test/resources/files/output.txt";
        final String outputGeneratedFilePath = "src/test/resources/files/output2.txt";
        final String[] args = {"-i", inputFilePath, "-o", outputGeneratedFilePath};

        DrawingTool.main(args);

        boolean success = false;

        try {
            final File o1 = new File(outputOriginalFilePath);
            final File o2 = new File(outputGeneratedFilePath);

            success = FileUtils.contentEqualsIgnoreEOL(o1, o2, StandardCharsets.UTF_8.name());
        } catch (final IOException e) {
            e.printStackTrace();

            success = false;
        }

        assertTrue(success);
    }
}
