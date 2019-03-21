"
I'm the super of all adapter of the application. My goal is to provide so additionnal Api that Famix entities doesn'
		t provide originaly
"
Class {
	#name : #MMCAbstractAdapter,
	#superclass : #Object,
	#instVars : [
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

{ #category : #visiting }
MMCAbstractAdapter >> acceptAssociationQuery [
	^ self subclassResponsibility
]

{ #category : #visiting }
MMCAbstractAdapter >> acceptNavigationQuery [ 
	^ self subclassResponsibility
]

{ #category : #visiting }
MMCAbstractAdapter >> acceptPropertyQuery [ 
	^ self subclassResponsibility
]

{ #category : #accessing }
MMCAbstractAdapter >> associations [

]

{ #category : #initialization }
MMCAbstractAdapter >> initialize [
	super initialize.
	
]

{ #category : #accessing }
MMCAbstractAdapter >> model: aModel [
	model := aModel.
	"self initQueries"
]

{ #category : #utilities }
MMCAbstractAdapter >> navigations [
]

{ #category : #accessing }
MMCAbstractAdapter >> properties [
	^ model first allDeclaredProperties
]

{ #category : #utilities }
MMCAbstractAdapter >> searchEntitiesWithParam: aDictionary [
	| text |
	text := String
		streamContents: [ :strem | 
			strem
				nextPutAll: 'each ';
				nextPutAll: (aDictionary at: 'property') name;
				space;
				nextPutAll: (aDictionary at: 'operator');
				space;
				nextPutAll: (aDictionary at: 'value') ].
			^ MVSFilterManager new executeQueryWith: text on:model  
]
