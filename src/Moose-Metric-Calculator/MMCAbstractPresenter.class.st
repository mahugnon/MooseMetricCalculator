"
I'm the super of all gui of the application. I allow them to have acces to the mandator(the context)
"
Class {
	#name : #MMCAbstractPresenter,
	#superclass : #DynamicComposablePresenter,
	#instVars : [
		'context',
		'mmcAdapter'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #'as yet unclassified' }
MMCAbstractPresenter >> getAdapter [
	mmcAdapter := MMCAbstractAdapter adapterFor: context focus
]

{ #category : #accessing }
MMCAbstractPresenter >> operations [
	^ #(= < >) asOrderedCollection
]

{ #category : #subscription }
MMCAbstractPresenter >> subscribeTo: aContext [
	aContext addListener: self.
	context := aContext
]

{ #category : #subscription }
MMCAbstractPresenter >> update [
	context activeFormPresenter update
]
