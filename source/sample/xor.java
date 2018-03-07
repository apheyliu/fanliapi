/**
 * 订单加密
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.codec.binary.Base64;

private String simple_xor(String str, String key) {
    if (StringUtils.isEmpty(str) || StringUtils.isEmpty(key)) {
        return "";
    }

    // str字符串的byte数组,并初始化字节流! 因为php中文字符串的str[i] == Java中字节流的第i次读取
    byte[] strBytes = str.getBytes("utf-8");
    InputStream strInputStream = new ByteArrayInputStream(strBytes, 0, strBytes.length);

    // key只需拿到byte数组的长度即可, 不用转成字节流
    byte[] keyBytes = key.getBytes("utf-8");
    int keyLen = keyBytes.length;

    // 存放计算后的字节数组
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    int j = 0, read;
    while ((read = strInputStream.read()) != -1) {
        // 将计算结果写入字节流, 因为php .= 运算会将原来的字节流再转换成String, 
        // 类似这样的处理: new String(bos.getByte(), 0, strBytes.length);
        bos.write(read ^ keyBytes[j]);
        j = (++j) % keyLen;
    }

    // 返回base64的结果
    return Base64.encodeBase64String(ArrayUtils.subarray(bos.toByteArray(), 0, strBytes.length));
}