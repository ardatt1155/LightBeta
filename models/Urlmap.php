<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Urlmap".
 *
 * @property integer $uid
 * @property string $url
 * @property string $hash
 */
class Urlmap extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'Urlmap';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['url', 'hash'], 'string', 'max' => 255]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'uid' => 'Uid',
            'url' => 'Url',
            'hash' => 'Hash',
        ];
    }

    /**
     * @inheritdoc
     * @return UrlmapQuery the active query used by this AR class.
     */
    public static function find()
    {
        return new UrlmapQuery(get_called_class());
    }
}
