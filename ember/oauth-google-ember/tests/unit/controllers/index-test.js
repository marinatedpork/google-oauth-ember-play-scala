import { moduleFor, test } from 'ember-qunit';

moduleFor('controller:index', {
  needs: ['controller:workbook', 'controller:application']
});

test('reads the application controller', function(assert) {
  let controller = this.subject();
  let applicationController = controller.get('applicationController');
  assert.ok(applicationController.isController, 'is an instance of a controller');
});

test('reads the application controller\'s isError property', function(assert) {
  let controller = this.subject();
  let applicationController = controller.get('applicationController');
  applicationController.set('error', 'true');
  assert.ok(controller.get('isError'));
});
