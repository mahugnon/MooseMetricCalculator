Class {
	#name : #MMCContext,
	#superclass : #Object,
	#instVars : [
		'listeners',
		'mooseModel',
		'focus',
		'activeFormPresenter'
	],
	#category : #'Moose-Metric-Calculator-Context'
}

{ #category : #adding }
MMCContext >> addListener: aListener [
	listeners add: aListener
]

{ #category : #initialization }
MMCContext >> initialize [
	super initialize.
	listeners := OrderedCollection new
]

{ #category : #actions }
MMCContext >> notifyAll [
	^ listeners
		ifEmpty: [ ^ self ];
		do: [ :each | each update ]
]

{ #category : #'as yet unclassified' }
MMCContext >> queriesForActiveForm [
	| adapter |
	adapter := MMCAbstractAdapter adapterFor: focus.
	^ adapter queriesFor: activeFormPresenter
]
