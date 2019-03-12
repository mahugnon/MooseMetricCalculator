Class {
	#name : #MMCAbstractAdapter,
	#superclass : #Object,
	#instVars : [
		'entity'
	],
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #'instance creation' }
MMCAbstractAdapter class >> adapterFor: aModel [
	| adapter |
	adapter := self allSubclasses
		select: [ :each | each adaptedClass = aModel class ].
	^ adapter isEmpty
		ifTrue: [ self new ]
		ifFalse: [ adapter first new ]
]

{ #category : #accessing }
MMCAbstractAdapter >> properties [
	^ entity allDeclaredProperties
]

{ #category : #'as yet unclassified' }
MMCAbstractAdapter >> queriesFor: aFormPresenter [
	^ self perform: aFormPresenter displayName asSymbol
]
