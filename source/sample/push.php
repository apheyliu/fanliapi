<?php
/**
 * post方式推送给返利
 * @version 1.0
 */
function post2Fanli($orderData) {
    $post_data = array(
        "content" => $orderData,
    );
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, 'http://union.fanli.com/dingdan/push/shopid/****');
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $post_data);
    //推送结果
    $re = curl_exec($ch);
    curl_close($ch);
    return $re;
}