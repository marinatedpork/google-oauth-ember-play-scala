import { moduleFor, test } from 'ember-qunit';
import Ember from 'ember';
import fakeReload from '../../helpers/fake-reload';

moduleFor('route:workbook', 'Unit | Route | workbook', {
  needs: ['model:workbook', 'model:worksheet', 'controller:workbook']
});

const TITLE = 'Taxes 2015';

test('sets up the controller with workbook title', function(assert) {
  let route = this.subject();
  let controller = route.controllerFor('workbook');
  Ember.run(function() {
    let workbook = route.store.createRecord('workbook', {id: 1, title: TITLE});
    let noTitle = controller.get('title');
    workbook.reload = fakeReload(workbook);
    assert.notOk(noTitle, 'controller has no title before route.setupController');
    route.get('setupController')(controller, workbook);
    assert.equal(controller.get('title'), TITLE, 'controller has workbook title after route.setupController');
  });
});

test('sets up the controller with workbook', function(assert) {
  let route = this.subject();
  let controller = route.controllerFor('workbook');
  Ember.run(function() {
    let workbook = route.store.createRecord('workbook', {id: 1});
    workbook.reload = fakeReload(workbook);
    route.get('setupController')(controller, workbook);
    assert.equal(controller.get('model'), workbook, 'controller has workbook title after route.setupController');
  });
});