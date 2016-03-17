import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('worksheet-table-row-index', 'Integration | Component | worksheet table row index', {
  integration: true
});

test('it renders the index', function(assert) {
  this.set('index', 1);
  this.render(hbs`{{worksheet-table-row-index index=index}}`);
  assert.equal(this.$().text().trim(), '1');
});
