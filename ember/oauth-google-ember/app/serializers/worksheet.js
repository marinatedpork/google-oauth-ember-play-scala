import DS from 'ember-data';

const { RESTSerializer, EmbeddedRecordsMixin } = DS;

export default RESTSerializer.extend(EmbeddedRecordsMixin, {
	attrs: {
		cells: {embedded: 'always'}
	}
});
