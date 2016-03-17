import DS from 'ember-data';
import Ember from 'ember';

const { computed } = Ember;
const { attr, belongsTo, hasMany, Model } = DS;

/**
 * The rows property is what forms the grid from a list of cells.
 */

export default Model.extend({
  title:    attr('string'),
  workbook: belongsTo('workbook', { async: true }),
  cells:    hasMany('cell', { async: true }),
  rows:     computed('cells', function() {
		return this.get('cells').reduce( (accum, cell) => {
			let row = cell.get('row');
			if (accum[row]) {
				accum[row].push(cell);
			} else {
				accum[row] = [cell];
			}
			return accum;
		}, [])
		.map(row => row.sortBy('column'));
  })
});