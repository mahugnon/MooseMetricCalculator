"
I'm the super of all adapter of the application. My goal is to provide so additionnal Api that Famix entities doesn'
		t provide originaly
"
Class {
	#name : #MMCAbstractAdapter,
	#superclass : #Object,
	#instVars : [
		'queries'
	],
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #'as yet unclassified' }
MMCAbstractAdapter class >> adaptedClass [
	^ self subclassResponsibility
]

{ #category : #'instance creation' }
MMCAbstractAdapter class >> adapterFor: aModel [
	| adapter |
	adapter := self allSubclasses
		select: [ :each | each adaptedClass = aModel class ].
	^ adapter isEmpty
		ifTrue: [ self new ]
		ifFalse: [ adapter first new]
]

{ #category : #accessing }
MMCAbstractAdapter >> associations [

]

{ #category : #'as yet unclassified' }
MMCAbstractAdapter >> initQueries [
	queries := Dictionary new.
	queries at: 'Property' put: self properties.
	queries at: 'Navigation' put: self navigations.
	queries at: 'Associations' put: self associations
]

{ #category : #initialization }
MMCAbstractAdapter >> initialize [ 
super initialize .
self initQueries
]

{ #category : #'as yet unclassified' }
MMCAbstractAdapter >> navigations [
]

{ #category : #accessing }
MMCAbstractAdapter >> properties [
	^ self subclassResponsibility
]

{ #category : #'as yet unclassified' }
MMCAbstractAdapter >> queriesFor: aFormPresenter [
	^ queries at: aFormPresenter name
]
