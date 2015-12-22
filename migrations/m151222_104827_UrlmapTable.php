<?php

use yii\db\Schema;
use yii\db\Migration;

class m151222_104827_UrlmapTable extends Migration
{
    private $table = 'Urlmap';

    public function safeUp()
    {
        $this->createTable($this->table, array(
            'uid' => Schema::TYPE_PK,
            'url' => Schema::TYPE_STRING,
            'hash' => Schema::TYPE_STRING
        ));
    }

    public function safeDown()
    {
        $this->dropTable($this->table);
    }
}
