package com.hugeinc.drawingtool.common.io;

import com.hugeinc.drawingtool.model.Canvas;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    public static List<String> getLines(final String filePath) {
        List<String> lineList = Collections.emptyList();

        if (StringUtils.isNotBlank(filePath)) {
            try (final Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lineList = lines.collect(Collectors.toList());
            } catch (final IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }

        return lineList;
    }

    public static boolean deleteFile(final String filePath) {
        if (StringUtils.isNotBlank(filePath)) {
            try {
                final File file = new File(filePath);

                if (file.exists()) {
                    file.delete();

                    return true;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean appendData(final String filePath, final String data) {
        if (StringUtils.isNotBlank(data) && StringUtils.isNotBlank(filePath)) {
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
                final File file = new File(filePath);

                if (!file.exists()) {
                    file.createNewFile();
                }

                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);

                bw.write(data);
            } catch (final IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return false;
    }

}
