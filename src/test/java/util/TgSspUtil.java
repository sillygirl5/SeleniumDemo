package util;


import tgssp.TgSsp;
public class TgSspUtil {

    /***
     * 获取第三方接入API request
     *
     * @param sourceDeviceId
     * @param md5DeviceId
     * @param sourceId
     * @param isMulti
     * @param width
     * @param height
     * @return
     */
    public static byte[] getRequest(String impressionId, String deviceIp, String sourceDeviceId, String md5DeviceId, String sourceId, boolean isMulti, int width, int height) {
        TgSsp.Request.Builder builder = TgSsp.Request.newBuilder();
        TgSsp.Request.Source.Builder sourceBuilder = TgSsp.Request.Source.newBuilder();
        TgSsp.Request.Device.Builder deviceBuilder = TgSsp.Request.Device.newBuilder();
        /**媒体资源位id*/
        sourceBuilder.setImpressionId(impressionId);
        /**天宫广告id*/
        sourceBuilder.setSourceId(sourceId);
        /**素材宽度*/
        sourceBuilder.setWidth(width);
        /**素材高度*/
        sourceBuilder.setHeight(height);
        /**是否多个素材*/
        sourceBuilder.setIsMulti(isMulti);
        /**是否全屏*/
        sourceBuilder.setIsFullscreen(true);
        /**目标设备Ip*/
        deviceBuilder.setIp(deviceIp);
        /**目标设备MD5后的编号*/
        deviceBuilder.setDeiviceId(md5DeviceId);
        /**目标设备原始编号*/
        deviceBuilder.setIdentifier(sourceDeviceId);
        builder.setSource(sourceBuilder);
        builder.setDevice(deviceBuilder);
        return builder.build().toByteArray();
    }
}
