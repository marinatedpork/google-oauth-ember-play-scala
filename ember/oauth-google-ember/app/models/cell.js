import DS from 'ember-data';

const { attr, belongsTo, Model } = DS;

export default Model.extend({
  workbook:  belongsTo('workbook', {async: true}),
  worksheet: belongsTo('worksheet', {async: true}),
  row:       attr('number'),
  column:    attr('number'),
  value:     attr('string')
});
