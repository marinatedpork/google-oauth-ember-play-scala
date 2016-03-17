import { moduleFor, test } from 'ember-qunit';
import Ember from 'ember';
import fakeReload from '../../../helpers/fake-reload';

moduleFor('route:workbook/worksheet', 'Unit | Route | workbook/worksheet', {
  needs: ['model:worksheet', 'model:workbook', 'model:cell', 'controller:workbook/worksheet']
});

test('sets up the controller with worksheet as model', function(assert) {
  let route = this.subject();
  let controller = route.controllerFor('workbook/worksheet');
  Ember.run(function() {
    let worksheet = route.store.createRecord('worksheet', {id: 1});
    worksheet.reload = fakeReload(worksheet);
    route.get('setupController')(controller, worksheet);
    assert.equal(controller.get('model'), worksheet);
  });
});