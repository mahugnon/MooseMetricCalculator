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

{ #category : #utilities }
MMCContext >> getProperties [
	^ self getAdapter properties
]

{ #category : #initialization }
MMCContext >> initialize [
	super initialize.
	listeners := OrderedCollection new.
	mooseModel := MooseModel root isEmpty
		ifTrue: [ OrderedCollection new ]
		ifFalse: [ MooseModel root last cacheMooseGroups entityCache ]
]

{ #category : #accesing }
MMCContext >> mooseModel [
	^ mooseModel
]

{ #category : #accessing }
MMCContext >> mooseModel: aModel [
	mooseModel := {aModel}.
	self notifyAll
]

{ #category : #actions }
MMCContext >> notifyAll [
	^ listeners
		ifEmpty: [ ^ self ];
		do: [ :each | each update ]
]

{ #category : #accessing }
MMCContext >> performSearchWithParameters: aDictionary [
	self
		mooseModel: (self getAdapter searchEntitiesWithParam: aDictionary)
]
