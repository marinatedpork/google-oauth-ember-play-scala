import { moduleFor, test } from 'ember-qunit';
import fakeTransition from '../../helpers/fake-transition';

moduleFor('route:workbooks', 'Unit | Route | workbooks', {
  needs: ['controller:application', 'controller:workbook']
});

test('sets Application Controllers showTitle property to false when in loading state', function(assert) {
  let route = this.subject();
  let applicationController = route.controllerFor('application');
  applicationController.set('showTitle', true);
  route.send('loading', fakeTransition);
  assert.notOk(applicationController.get('showTitle'));
});