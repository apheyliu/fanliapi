<?php
/**
 * 订单加密
 * @version 1.0
 */

function simple_xor($str,$key){ 
    $txt = '';
    if(empty($str) || empty($key)){ 
        return '';
    }
    $keylen = strlen($key); 
    $strlen = strlen($str);
    $j = 0; 
    for($i=0;$i<$strlen;$i++) {
       $txt .= $str[$i] ^ $key[$j];
       $j = (++$j) % ($keylen);
    }
    return base64_encode($txt);
}