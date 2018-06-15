package util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class CommonValuesUtils {

    private static final Logger LOGGER = Logger.getLogger(CommonValuesUtils.class);

    public static final String PROJECT_ID = "project_id";
    public static final String PROJECT_NAME = "project_name";
    public static final String PLAN_ID = "plan_id";
    public static final String PLAN_START_TIME = "plan_start_time";
    public static final String PLAN_END_TIME = "plan_end_time";
    public static final String MEDIA_TYPE = "media_type";
    public static final String MEDIA_NAME = "media_name";
    public static final String CHANNEL_NAME = "channel_name";
    public static final String PURCHASE_TYPE = "purchase_type";
    public static final String RESOURCE = "resource";
    public static final String UNIT_ID = "unit_id";
    public static final String DELIVERY_TYPE = "delivery_type";
    public static final String UNIT_RELATIONS_VO = "UnitRelationsVo";

    private static final String PATH = System.getProperty("user.dir") + File.separator + "tmp" + File.separator + "param_common.json";

    private static InputStream init() {
        InputStream is = null;
        try {
            is = new FileInputStream(new File(PATH));
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } finally {
            return is;
        }
    }

    /***
     * 获取公共变量值
     *
     * @param key
     * @return
     */
    public static Object getValue(String key) {
        JSONObject json = FileUtil.file2json(init());
        return json.get(key);
    }

    /**
     * 更改或者设置公共变量值
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, Object value) {
        JSONObject json = FileUtil.file2json(init());
        json.put(key, value);
        FileUtil.writeFile(json.toString(), PATH);
    }

    public static void main(String[] args) {
    }
}
