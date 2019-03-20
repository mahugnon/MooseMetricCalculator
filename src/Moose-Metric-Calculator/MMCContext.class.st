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
		'history',
		'query'
	],
	#category : #'Moose-Metric-Calculator-Context'
}

{ #category : #adding }
MMCContext >> addListener: aListener [
	listeners add: aListener
]

{ #category : #acccessing }
MMCContext >> focus [
	^ focus
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

{ #category : #'history saving' }
MMCContext >> history: aMMCHistory [
 history:= aMMCHistory
	
]

{ #category : #initialization }
MMCContext >> initialize [
	super initialize.
	query := MMCQuery new.
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
	mooseModel := aModel.
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
	history updatePast.
	self
		mooseModel: {(self getAdapter searchEntitiesWithParam: aDictionary)} 
]

{ #category : #'queries - protocols' }
MMCContext >> queryAssociation: anAssociation [
	query association: anAssociation
]

{ #category : #'queries - protocols' }
MMCContext >> queryDirection: aString [
	query direction: aString
]

{ #category : #'queries - protocols' }
MMCContext >> queryFamixClass: aFamixClass [
	query famixClass: aFamixClass
]

{ #category : #'queries - protocols' }
MMCContext >> queryMethod: aString [
	query method: aString
]

{ #category : #accessing }
MMCContext >> searchAssociations [
	^ query searchAssociations
]
