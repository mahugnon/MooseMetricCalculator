"
I'm the adapter for FAMIXClass
"
Class {
	#name : #MMCFAMIXClassAdapter,
	#superclass : #MMCAbstractAdapter,
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #'as yet unclassified' }
MMCFAMIXClassAdapter class >> adaptedClass [
	^ FAMIXClass
]

{ #category : #visiting }
MMCFAMIXClassAdapter >> acceptAssociationQuery [
	^ true
]

{ #category : #visiting }
MMCFAMIXClassAdapter >> acceptNavigationQuery [ 
	^ true
]

{ #category : #visiting }
MMCFAMIXClassAdapter >> acceptPropertyQuery [ 
	^ false
]
