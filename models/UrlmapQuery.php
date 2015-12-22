<?php

namespace app\models;

/**
 * This is the ActiveQuery class for [[Urlmap]].
 *
 * @see Urlmap
 */
class UrlmapQuery extends \yii\db\ActiveQuery
{
    /*public function active()
    {
        $this->andWhere('[[status]]=1');
        return $this;
    }*/

    /**
     * @inheritdoc
     * @return Urlmap[]|array
     */
    public function all($db = null)
    {
        return parent::all($db);
    }

    /**
     * @inheritdoc
     * @return Urlmap|array|null
     */
    public function one($db = null)
    {
        return parent::one($db);
    }
}