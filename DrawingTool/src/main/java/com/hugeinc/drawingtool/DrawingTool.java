package com.hugeinc.drawingtool;

import com.hugeinc.drawingtool.common.DrawingToolConstants;
import com.hugeinc.drawingtool.common.io.CommandUtils;
import com.hugeinc.drawingtool.common.io.FileUtils;
import com.hugeinc.drawingtool.model.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DrawingTool {

    public static void main(final String[] args) {
        final Options arguments = CommandUtils.getArgumentOptions();
        final CommandLine cmd = CommandUtils.getCommandLine(DrawingToolConstants.DRAWING_TOOL_NAME, arguments, args);

        if (cmd != null) {
            final String inputPath = cmd.getOptionValue(DrawingToolConstants.ARG_OPTION_INPUT);

            if (StringUtils.isNotBlank(inputPath)) {
                final List<String> commands = FileUtils.getLines(inputPath);

                if (!commands.isEmpty()) {
                    String outputPath = cmd.getOptionValue(DrawingToolConstants.ARG_OPTION_OUTPUT);

                    if (StringUtils.isBlank(outputPath)) {
                        outputPath = DrawingToolConstants.DEFAULT_OUTPUT_FILE;
                    }

                    FileUtils.deleteFile(outputPath);

                    Canvas canvas = null;
                    boolean canvasCreated = false;
                    boolean validCmd;

                    for (final String command : commands) {
                        final String[] cmdArgs = StringUtils.split(command);

                        validCmd = false;

                        if (cmdArgs != null) {
                            switch (cmdArgs[0]) {
                                case DrawingToolConstants.DRAWING_OPTION_CREATE_CANVAS:
                                    if (!canvasCreated && cmdArgs.length == DrawingToolConstants.DRAWING_OPTION_CREATE_CANVAS_ARGS) {
                                        int width = 0;
                                        int height = 0;

                                        if (StringUtils.isNumeric(cmdArgs[1]) && StringUtils.isNumeric(cmdArgs[2])) {
                                            try {
                                                width = Integer.parseInt(cmdArgs[1]);
                                                height = Integer.parseInt(cmdArgs[2]);
                                            } catch (final NumberFormatException nfe) {
                                                nfe.printStackTrace();
                                            }
                                        }

                                        if (width > 0 && height > 0) {
                                            canvas = new Canvas(width, height);

                                            canvasCreated = true;
                                            validCmd = true;
                                        }
                                    }

                                    break;
                                case DrawingToolConstants.DRAWING_OPTION_CREATE_LINE:
                                    if (canvasCreated) {
                                        if (cmdArgs.length == DrawingToolConstants.DRAWING_OPTION_CREATE_LINE_ARGS) {
                                            DrawableShape line = null;

                                            if (StringUtils.isNumeric(cmdArgs[1]) && StringUtils.isNumeric(cmdArgs[2]) && StringUtils.isNumeric(cmdArgs[3]) && StringUtils.isNumeric(cmdArgs[4])) {
                                                try {
                                                    final int x1 = Integer.parseInt(cmdArgs[1]);
                                                    final int y1 = Integer.parseInt(cmdArgs[2]);
                                                    final int x2 = Integer.parseInt(cmdArgs[3]);
                                                    final int y2 = Integer.parseInt(cmdArgs[4]);

                                                    line = new Line(x1, y1, x2, y2);
                                                } catch (final NumberFormatException nfe) {
                                                    nfe.printStackTrace();
                                                }

                                                if (line != null) {
                                                    line.draw(canvas);

                                                    validCmd = true;
                                                }
                                            }
                                        }
                                    }

                                    break;
                                case DrawingToolConstants.DRAWING_OPTION_CREATE_RECTANGLE:
                                    if (canvasCreated) {
                                        if (cmdArgs.length == DrawingToolConstants.DRAWING_OPTION_CREATE_RECTANGLE_ARGS) {
                                            DrawableShape rectangle = null;

                                            if (StringUtils.isNumeric(cmdArgs[1]) && StringUtils.isNumeric(cmdArgs[2]) && StringUtils.isNumeric(cmdArgs[3]) && StringUtils.isNumeric(cmdArgs[4])) {
                                                try {
                                                    final int x1 = Integer.parseInt(cmdArgs[1]);
                                                    final int y1 = Integer.parseInt(cmdArgs[2]);
                                                    final int x2 = Integer.parseInt(cmdArgs[3]);
                                                    final int y2 = Integer.parseInt(cmdArgs[4]);

                                                    rectangle = new Rectangle(x1, y1, x2, y2);
                                                } catch (final NumberFormatException nfe) {
                                                    nfe.printStackTrace();
                                                }

                                                if (rectangle != null) {
                                                    rectangle.draw(canvas);

                                                    validCmd = true;
                                                }
                                            }
                                        }
                                    }

                                    break;
                                case DrawingToolConstants.DRAWING_OPTION_BUCKET_FILL:
                                    if (canvasCreated) {
                                        if (cmdArgs.length == DrawingToolConstants.DRAWING_OPTION_BUCKET_FILL_ARGS) {
                                            Point point = null;
                                            char color = '\u0000';

                                            if (StringUtils.isNumeric(cmdArgs[1]) && StringUtils.isNumeric(cmdArgs[2]) && StringUtils.isAlpha(cmdArgs[3]) && StringUtils.length(cmdArgs[3]) == 1) {
                                                try {
                                                    final int x = Integer.parseInt(cmdArgs[1]);
                                                    final int y = Integer.parseInt(cmdArgs[2]);
                                                    point = new Point(x, y);

                                                    color = cmdArgs[3].charAt(0);
                                                } catch (final NumberFormatException nfe) {
                                                    nfe.printStackTrace();
                                                }

                                                if (point != null && point.isValid(canvas)) {
                                                    canvas.fillCanvas(canvas, point, color);

                                                    validCmd = true;
                                                }
                                            }
                                        }
                                    }

                                    break;
                                default:
                                    System.out.println(String.format("Invalid drawing option: '%s'", cmdArgs[0]));

                                    break;
                            }

                            if (validCmd) {
                                FileUtils.appendData(outputPath, canvas.toString());
                                //System.out.print(canvas.toString());
                            }
                        }
                    }
                }
            }
        }
    }

}
