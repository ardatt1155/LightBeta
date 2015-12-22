<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Urlmaps';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="urlmap-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Urlmap', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'uid',
            'url:url',
            'hash:url',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
