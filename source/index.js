
var express = require('express');
var mongoose = require('mongoose');

var application = express();

mongoose.connect('mongodb://moose:moose@ds027345.mongolab.com:27345/moth');
application.use('/static', express.static(__dirname + '/angular'));


var database = mongoose.connection;
database.on('error', console.error.bind(console, 'Connection error.'));
database.once('open', () => {
	console.log('Connection complete.');
});


var Schema = mongoose.Schema;

var TodoSchema = new Schema({
	title: String,
	author: String,
	description: String,
	when: {
		type: Date,
		default: Date.now
	}
});
var Todo = mongoose.model('Todo', TodoSchema);



application.get('/api/todos/show', function (request, response) {
	Todo.find(function (error, models) {
		if (error) response.send(error)
		else response.json(models);
	});
});

application.put('/api/todos/put', function (request, response) {
	Todo.create({
		text: request.body.text,
		done: false
	}, function (error, response) {
		if (error) response.send(error)
		else Todo.find(function (error, response) {
			if (error) response.send(error);
			else response.json(todos);
		});
	});
});

application.delete('/api/todos/:uid', function (request, response) {
	Todo.remove({
		_id: request.params.uid
	}, function (error, model) {
		if (error) response.send(error);
		else response.json(model);
	});
});


application.get('/index', function (request, response) {
	response.sendfile('./angular/index.html');
});


var server = application.listen(8080, function () {
	var host = server.address().address;
  	var port = server.address().port;
	console.log('Application listening at http://%s:%s', host, port);
});


