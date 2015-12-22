<?php

namespace app\components;

use Yii;
use yii\helpers\BaseUrl;
use app\components\ShortenerInterface;

class Urlshortener implements ShortenerInterface
{
    public function shorten($url)
    {
        //$base = BaseUrl::home();
        // @todo Get the base-url from Yii
        $base = 'http://localhost:8888/';
        $base = $base . 'urlmap/goto/';

        $server = Yii::$app->params['serverUid'];
        $date = new \DateTime();
        $timestamp = $date->getTimestamp();
        $process = getmypid();
        $thread = rand(1, 1000); // \Thread::getCurrentThreadId(); // @todo Resolve Thread and get rid of rand()

        $uid = $timestamp . $server . $process . $thread;
        $gmp = gmp_init($uid);
        $hash = '';
        for ($it = gmp_init('36'); gmp_cmp($gmp, gmp_init('0')) > 0; $it = gmp_mul($it, gmp_init('36')))
        {
            $remainder = gmp_intval(gmp_mod($gmp, $it));
            $character = $remainder > 25 ? ($remainder - 26) : chr(ord('A') + $remainder);
            $hash = $character . $hash;
        }
        return $base . $hash;
    }
}
