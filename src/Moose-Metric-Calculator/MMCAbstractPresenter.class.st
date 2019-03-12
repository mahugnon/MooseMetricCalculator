Class {
	#name : #MMCAbstractPresenter,
	#superclass : #DynamicComposablePresenter,
	#instVars : [
		'context'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #subscription }
MMCAbstractPresenter >> subscribeTo: aContext [
	aContext addListener: self.
	context := aContext
]

{ #category : #subscription }
MMCAbstractPresenter >> update [
	^ self subclassResponsibility
]
