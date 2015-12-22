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
        $serverUid = Yii::$app->params['serverUid'];
        return $base . $serverUid . '666';
    }
}
