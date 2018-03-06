/**
 * post方式推送给返利
 * @version 1.0
 */
private String post2Fanli(String orderData) {
    if (StringUtils.isNotBlank(orderData)) {
        try {
            //转义
            orderData = URLEncoder.encode(orderData, "utf-8");
            String formData = "content=" + orderData;
            URL url = new URL("http://union.fanli.com/dingdan/push/shopid/****");
            System.out.println(url.getPath());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true); // 设置post方式
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            
            DataOutputStream out = new DataOutputStream(conn.getOutputStream()); // 获得连接输出流
            out.writeBytes(formData);
            out.flush();
            out.close();
            
            //设置编码,否则中文乱码
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            // 接收返回代码
            StringBuffer result = new StringBuffer();
            String s = null;
            while ((s = reader.readLine()) != null) {
                result.append(s);
            }
            reader.close();
            conn.disconnect();
            //返回推送结果
            //Logger.info("推单结果", result.toString());
            return result.toString();
        } catch (Exception e) {
            //Logger.error("订单推送失败",orderData);
        }
    }
    return null;
}