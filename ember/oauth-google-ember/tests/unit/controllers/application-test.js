import { moduleFor, test } from 'ember-qunit';
import Ember from 'ember';

moduleFor('controller:application', {
  needs: ['controller:workbook']
});

test('has query parameters', function(assert) {
  let controller = this.subject();
  let queryParams = controller.get('queryParams');
  assert.ok(queryParams.length);
});

test('contains \'error\' in its query parameters', function(assert) {
  let controller = this.subject();
  let queryParams = controller.get('queryParams');
  assert.ok(queryParams.contains('error'));
});

test('sets \'isError\' to true when the value of the query parameter is true', function(assert) {
  let controller = this.subject();
  controller.set('error', 'true');
  assert.ok(controller.get('isError'));
});

test('observes the target.url', function(assert) {
  let controller = this.subject();
  assert.ok(controller.hasObserverFor('target.url'));
});

test('reads the workbook controller', function(assert) {
  let controller = this.subject();
  let workbookController = controller.get('workbookController');
  assert.ok(workbookController.isController, 'property is an instance of a controller');
});

test('reads workbookController\'s title property', function(assert) {
  let controller = this.subject();
  let workbookTitle = 'Taxes';
  let workbookController = Ember.Object.create({
  	title: workbookTitle
  });
  controller.set('workbookController', workbookController);
  let controllerTitle = controller.get('title');
  assert.equal(controllerTitle, workbookTitle, 'workbookTitle is the same as controller title');
});

test('sets \'showTitle\' to true when currentPath does not contain \'workbooks\'', function(assert) {
  let controller = this.subject();
  let sender = Ember.Object.create({
  	currentPath: 'workbook.index'
  });
  assert.notOk(controller.get('showTitle'));
  controller.observesTitle(sender);
  assert.ok(controller.get('showTitle'));
});