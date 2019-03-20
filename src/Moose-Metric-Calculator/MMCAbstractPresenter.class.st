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

{ #category : #utilities }
MMCAbstractPresenter >> getAdapter [
	mmcAdapter := MMCAbstractAdapter adapterFor: context focus
]

{ #category : #'as yet unclassified' }
MMCAbstractPresenter >> getContext [
	^ context ifNil: [ ^ context := MMCContext new ]
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

{ #category : #updating }
MMCAbstractPresenter >> update [
	"context activeFormPresenter update"
]
