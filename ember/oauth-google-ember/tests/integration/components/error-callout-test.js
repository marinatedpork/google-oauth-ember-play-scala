import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('error-callout', 'Integration | Component | error callout', {
  integration: true
});

const UserAppSettings = 'https://security.google.com/settings/security/permissions';

test('contains a link to the Google user app settings', function(assert) {
  this.render(hbs`{{error-callout}}`);
  assert.equal(this.$('a').attr('href').trim(), UserAppSettings);
});
