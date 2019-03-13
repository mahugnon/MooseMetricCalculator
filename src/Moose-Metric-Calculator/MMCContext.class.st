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

{ #category : #accessing }
MMCContext >> activeFormPresenter [
	^ activeFormPresenter
]

{ #category : #accessing }
MMCContext >> activeFormPresenter: aMooseQueryFormPresenter [
	activeFormPresenter := aMooseQueryFormPresenter
]

{ #category : #adding }
MMCContext >> addListener: aListener [
	listeners add: aListener
]

{ #category : #accessing }
MMCContext >> focus: aMooseModel [
	focus := aMooseModel.
	self notifyAll
]

{ #category : #utilities }
MMCContext >> getAdapter [
	^ MMCAbstractAdapter adapterFor: focus
]

{ #category : #initialization }
MMCContext >> initialize [
	super initialize.
	listeners := OrderedCollection new.
	mooseModel := MooseModel root isEmpty
		ifTrue: [ OrderedCollection new ]
		ifFalse: [ MooseModel root last allModelClasses ].
	activeFormPresenter := MMCMoosePropertyFormPresenter
]

{ #category : #accesing }
MMCContext >> mooseModel [
	^ mooseModel
]

{ #category : #actions }
MMCContext >> notifyAll [
	^ listeners
		ifEmpty: [ ^ self ];
		do: [ :each | each update ]
]

{ #category : #utilities }
MMCContext >> queriesForActiveForm [
	| adapter |
	adapter := self getAdapter.
	^ adapter queriesFor: activeFormPresenter
]
