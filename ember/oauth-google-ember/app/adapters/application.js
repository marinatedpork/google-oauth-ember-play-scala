import DS from 'ember-data';

const { RESTAdapter } = DS;

/**
 * Structures the URLs based on the type of model
 */
export default RESTAdapter.extend({
  namespace: 'xhr',
  urlForFindRecord(id, resource, snapshot) {
    switch(resource) {
      case 'workbook':
        return `xhr/workbooks/${id}`;
      case 'worksheet':
        let workbookId = snapshot.record.get('workbook.id');
        return `xhr/workbooks/${workbookId}/worksheets/${id}`;
      default:
        return `xhr/error`;
    }
  }
});
