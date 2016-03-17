import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('authenticate-button', 'Integration | Component | authenticate button', {
  integration: true
});

test('contains the word Authenticate', function(assert) {
  this.render(hbs`{{authenticate-button}}`);
  assert.equal(this.$().text().trim(), 'Authenticate');
});

test('is a button tag', function(assert) {
  this.render(hbs`{{authenticate-button}}`);
  assert.ok(this.$('button').text().trim());
});

test('has btn and btn-primary class', function(assert) {
  assert.expect(2);
  this.render(hbs`{{authenticate-button}}`);
  assert.ok(this.$('button').hasClass('btn'));
  assert.ok(this.$('button').hasClass('btn-primary'));
});
