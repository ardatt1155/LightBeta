<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Urlmap */

$this->title = 'Create Urlmap';
$this->params['breadcrumbs'][] = ['label' => 'Urlmaps', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="urlmap-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
