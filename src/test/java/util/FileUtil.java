package util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * 文件处理工具类
 *
 * @author cdzhaojiajun
 * @create 2018/4/16
 */
public class FileUtil {
    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    /**
     * 将文件中的内容转换成jsonobject对象
     *
     * @param path
     * @return
     */
    public static JSONObject file2json(String path) {
        JSONObject json = null;
        try {
            InputStream is = new FileInputStream(path);
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"), Feature.OrderedField);
        } catch (IOException e) {
            LOGGER.error("file 2 json exception happen.", e);
        }
        return json;
    }

    /**
     * 将文件中的内容转换成jsonobject对象
     *
     * @param is
     * @return
     */
    public static JSONObject file2json(InputStream is) {
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"), Feature.OrderedField);
        } catch (IOException e) {
            LOGGER.error("file 2 json exception happen.", e);
        }
        return json;
    }

    /**
     * 保存文件
     *
     * @param content
     * @param filePath
     */
    public static void writeFile(String content, String filePath) {
        File file = new File(filePath);
        File dir = new File(file.getParent());
        FileWriter writer = null;
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("Save file %s fail.", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }
    }
}
