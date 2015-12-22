<?php

namespace app\components;

use Yii;

interface ShortenerInterface
{
    // The implementation of the shortening-algorithm may or may not require the original-url itself.
    // But the interface must be generic across all algorithms. Hence the $url is included
    public function shorten($url);
}
