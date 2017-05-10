import Ember from 'ember';

const { Controller, inject, computed, observer } = Ember;

/**
 * Manages showing the title and catching errors when there's an API problem
 */

export default Controller.extend({
  queryParams:        ['error'],
  error:              null,
  workbookController: inject.controller('workbook'),
  title:              computed.reads('workbookController.title'),
  isError:            computed.equal('error', 'true'),
  observesTitle:      observer('target.url', function(sender) {
    this.set('showTitle', sender.get('currentPath') !== 'workbooks');
  })
});
