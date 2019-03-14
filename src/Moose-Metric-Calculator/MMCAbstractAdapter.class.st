"
I'm the super of all adapter of the application. My goal is to provide so additionnal Api that Famix entities doesn'
		t provide originaly
"
Class {
	#name : #MMCAbstractAdapter,
	#superclass : #Object,
	#instVars : [
		'queries',
		'model'
	],
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #utilities }
MMCAbstractAdapter class >> adaptedClass [
	^ self subclassResponsibility
]

{ #category : #utilities }
MMCAbstractAdapter class >> adapterFor: aModel [
	| adapter |
	aModel ifNil: [ ^ self new ].
	adapter := (self allSubclasses
		select:
			[ :each | aModel class withAllSuperclasses includes: each adaptedClass ])
		sorted: [ :a :b | b allSubclasses includes: a ].
	^ adapter isEmpty
		ifTrue: [ self new ]
		ifFalse: [ adapter first new model: aModel ]
]

{ #category : #accessing }
MMCAbstractAdapter >> associations [

]

{ #category : #utilities }
MMCAbstractAdapter >> initQueries [
	queries := Dictionary new.
	queries at: 'Property' put: self properties.
	queries at: 'Navigation' put: self navigations.
	queries at: 'Associations' put: self associations
]

{ #category : #initialization }
MMCAbstractAdapter >> initialize [
	super initialize.
	
]

{ #category : #accessing }
MMCAbstractAdapter >> model: aModel [
	model := aModel.
	self initQueries
]

{ #category : #utilities }
MMCAbstractAdapter >> navigations [
]

{ #category : #accessing }
MMCAbstractAdapter >> properties [
	^ model first allDeclaredProperties
]

{ #category : #utilities }
MMCAbstractAdapter >> queriesFor: aFormPresenter [
	^ queries at: aFormPresenter name
]

{ #category : #utilities }
MMCAbstractAdapter >> searchEntitiesWithParam: aDictionary [
	^ model
		select: [ :each | 
			(each perform: (aDictionary at: 'property') name asSymbol)
				perform: (aDictionary at: 'operator') asSymbol
				with: (aDictionary at: 'value') ]
]
