import { moduleFor, test } from 'ember-qunit';
import Ember from 'ember';

moduleFor('adapter:application', 'Unit | Adapter | application', {
	needs: ['model:worksheet', 'model:workbook'],
});

const bookId  = '1';
const sheetId = '2';
const workbookUrl = `xhr/workbooks/${bookId}`;
const worksheetUrl = `xhr/workbooks/${bookId}/worksheets/${sheetId}`;
const errorUrl = 'xhr/error';

test('builds a url for finding a workbook', function(assert) {
  let adapter = this.subject();
  let builtUrl = adapter.get('urlForFindRecord')(bookId, 'workbook');
  assert.equal(builtUrl, workbookUrl, 'should add xhr/workbooks/ to it');
});

test('builds a url for finding a worksheet', function(assert) {
  let adapter = this.subject();
  let snapshot = {
  	record: Ember.Object.create({
  		workbook: Ember.Object.create({
  			id: bookId
  		})
  	})
	};
  let builtUrl = adapter.get('urlForFindRecord')(sheetId, 'worksheet', snapshot);
  assert.equal(builtUrl, worksheetUrl, 'should get the bookId from the snapshot');
});

test('build an error url if resource is unknown', function(assert) {
  let adapter = this.subject();
  let builtUrl = adapter.get('urlForFindRecord')(0, 'pig');
  assert.equal(builtUrl, errorUrl);
});
