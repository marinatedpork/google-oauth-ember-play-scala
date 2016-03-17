import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

/**
 * Contains routes needed for resource pathing
 */

Router.map(function() {
  this.route('workbooks');
  this.route('workbook', { path: '/workbooks/:workbook_id' }, function() {
    this.route('worksheet', { path: '/worksheets/:worksheet_id' });
  });
});

export default Router;
