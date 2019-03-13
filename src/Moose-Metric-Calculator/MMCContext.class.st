"
I'm the mandator of all gui of the application 
"
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

{ #category : #'as yet unclassified' }
MMCContext >> getAdapter [
	^ MMCAbstractAdapter adapterFor: focus
]

{ #category : #initialization }
MMCContext >> initialize [
	super initialize.
	listeners := OrderedCollection new.
	mooseModel := MooseModel root last allModelClasses
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
	adapter := self getAdapter.
	^ adapter queriesFor: activeFormPresenter
]
